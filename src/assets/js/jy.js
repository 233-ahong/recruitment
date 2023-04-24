'v4.1.5 Geetest Inc.'
;(function (window) {
  'use strict'
  if (typeof window === 'undefined') {
    throw new Error('Geetest requires browser environment')
  }

  let document = window.document
  let Math = window.Math
  let head = document.getElementsByTagName('head')[0]
  let TIMEOUT = 10000

  function _Object(obj) {
    this._obj = obj
  }

  _Object.prototype = {
    _each: function (process) {
      let _obj = this._obj
      for (let k in _obj) {
        if (_obj.hasOwnProperty(k)) {
          process(k, _obj[k])
        }
      }
      return this
    },
    _extend: function (obj) {
      let self = this
      new _Object(obj)._each(function (key, value) {
        self._obj[key] = value
      })
    },
  }

  let uuid = function () {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(
      /[xy]/g,
      function (c) {
        let r = (Math.random() * 16) | 0
        let v = c === 'x' ? r : (r & 0x3) | 0x8
        return v.toString(16)
      }
    )
  }

  function Config(config) {
    let self = this
    new _Object(config)._each(function (key, value) {
      self[key] = value
    })
  }

  Config.prototype = {
    apiServers: [
      'gcaptcha4.geetest.com',
      'gcaptcha4.geevisit.com',
      'gcaptcha4.gsensebot.com',
    ],
    staticServers: [
      'static.geetest.com',
      'static.geevisit.com',
      'dn-staticdown.qbox.me',
    ],
    protocol: 'http://',
    typePath: '/load',
    fallback_config: {
      bypass: {
        staticServers: [
          'static.geetest.com',
          'static.geevisit.com',
          'dn-staticdown.qbox.me',
        ],
        type: 'bypass',
        bypass: '/v4/bypass.js',
      },
    },
    _get_fallback_config: function () {
      let self = this
      if (isString(self.type)) {
        return self.fallback_config[self.type]
      }
      return self.fallback_config.bypass
    },
    _extend: function (obj) {
      let self = this
      new _Object(obj)._each(function (key, value) {
        self[key] = value
      })
    },
  }
  let isNumber = function (value) {
    return typeof value === 'number'
  }
  var isString = function (value) {
    return typeof value === 'string'
  }
  let isBoolean = function (value) {
    return typeof value === 'boolean'
  }
  let isObject = function (value) {
    return typeof value === 'object' && value !== null
  }
  let isFunction = function (value) {
    return typeof value === 'function'
  }
  let MOBILE = /Mobi/i.test(navigator.userAgent)

  let callbacks = {}
  let status = {}

  let random = function () {
    return parseInt(Math.random() * 10000) + new Date().valueOf()
  }

  // bind 函数polify, 不带new功能的bind

  let bind = function (target, context) {
    if (typeof target !== 'function') {
      return
    }
    let args = Array.prototype.slice.call(arguments, 2)

    if (Function.prototype.bind) {
      return target.bind(context, args)
    }
    return function () {
      let _args = Array.prototype.slice.call(arguments)
      return target.apply(context, args.concat(_args))
    }
  }

  let toString = Object.prototype.toString

  let _isFunction = function (obj) {
    return typeof obj === 'function'
  }
  let _isObject = function (obj) {
    return obj === Object(obj)
  }
  let _isArray = function (obj) {
    return toString.call(obj) === '[object Array]'
  }
  let _isDate = function (obj) {
    return toString.call(obj) === '[object Date]'
  }
  let _isRegExp = function (obj) {
    return toString.call(obj) === '[object RegExp]'
  }
  let _isBoolean = function (obj) {
    return toString.call(obj) === '[object Boolean]'
  }

  function resolveKey(input) {
    return input.replace(/(\S)(_([a-zA-Z]))/g, function (match, $1, $2, $3) {
      return $1 + $3.toUpperCase() || ''
    })
  }

  function camelizeKeys(input, convert) {
    if (
      !_isObject(input) ||
      _isDate(input) ||
      _isRegExp(input) ||
      _isBoolean(input) ||
      _isFunction(input)
    ) {
      return convert ? resolveKey(input) : input
    }

    if (_isArray(input)) {
      var temp = []
      for (let i = 0; i < input.length; i++) {
        temp.push(camelizeKeys(input[i]))
      }
    } else {
      var temp = {}
      for (let prop in input) {
        if (input.hasOwnProperty(prop)) {
          temp[camelizeKeys(prop, true)] = camelizeKeys(input[prop])
        }
      }
    }
    return temp
  }

  let loadScript = function (url, cb, timeout) {
    let script = document.createElement('script')
    script.charset = 'UTF-8'
    script.async = true

    // 对geetest的静态资源添加 crossOrigin
    if (/static\.geetest\.com/g.test(url)) {
      script.crossOrigin = 'anonymous'
    }

    script.onerror = function () {
      cb(true)
      // 错误触发了，超时逻辑就不用了
      loaded = true
    }
    var loaded = false
    script.onload = script.onreadystatechange = function () {
      if (
        !loaded &&
        (!script.readyState ||
          'loaded' === script.readyState ||
          'complete' === script.readyState)
      ) {
        loaded = true
        setTimeout(function () {
          cb(false)
        }, 0)
      }
    }
    script.src = url
    head.appendChild(script)

    setTimeout(function () {
      if (!loaded) {
        script.onerror = script.onload = null
        script.remove && script.remove()
        cb(true)
      }
    }, timeout || TIMEOUT)
  }

  let normalizeDomain = function (domain) {
    // special domain: uems.sysu.edu.cn/jwxt/geetest/
    // return domain.replace(/^https?:\/\/|\/.*$/g, ''); uems.sysu.edu.cn
    return domain.replace(/^https?:\/\/|\/$/g, '') // uems.sysu.edu.cn/jwxt/geetest
  }
  let normalizePath = function (path) {
    path = path.replace(/\/+/g, '/')
    if (path.indexOf('/') !== 0) {
      path = '/' + path
    }
    return path
  }
  let normalizeQuery = function (query) {
    if (!query) {
      return ''
    }
    let q = '?'
    new _Object(query)._each(function (key, value) {
      if (isString(value) || isNumber(value) || isBoolean(value)) {
        q = q + encodeURIComponent(key) + '=' + encodeURIComponent(value) + '&'
      }
    })
    if (q === '?') {
      q = ''
    }
    return q.replace(/&$/, '')
  }
  let makeURL = function (protocol, domain, path, query) {
    domain = normalizeDomain(domain)

    let url = normalizePath(path) + normalizeQuery(query)
    if (domain) {
      url = protocol + domain + url
    }

    return url
  }

  let load = function (config, protocol, domains, path, query, cb, handleCb) {
    let tryRequest = function (at) {
      // 处理jsonp回调，这里为了保证每个不同jsonp都有唯一的回调函数
      if (handleCb) {
        var cbName = 'geetest_' + random()
        // 需要与预先定义好cbname参数，删除对象
        window[cbName] = bind(handleCb, null, cbName)
        query.callback = cbName
      }
      let url = makeURL(protocol, domains[at], path, query)
      loadScript(
        url,
        function (err) {
          if (err) {
            // 超时或者出错的时候 移除回调
            if (cbName) {
              try {
                window[cbName] = function () {
                  window[cbName] = null
                }
              } catch (e) {}
            }

            if (at >= domains.length - 1) {
              cb(true)
              // report gettype error
            } else {
              tryRequest(at + 1)
            }
          } else {
            cb(false)
          }
        },
        config.timeout
      )
    }
    tryRequest(0)
  }

  let jsonp = function (domains, path, config, callback) {
    let handleCb = function (cbName, data) {
      // 保证只执行一次，全部超时的情况下不会再触发;

      if (data.status === 'success') {
        callback(data.data)
      } else if (!data.status) {
        callback(data)
      } else {
        //接口有返回，但是返回了错误状态，进入报错逻辑
        callback(data)
      }
      window[cbName] = undefined
      try {
        delete window[cbName]
      } catch (e) {}
    }
    load(
      config,
      config.protocol,
      domains,
      path,
      {
        captcha_id: config.captchaId,
        challenge: config.challenge || uuid(),
        client_type: MOBILE ? 'h5' : 'web',
        risk_type: config.riskType,
        user_info: config.userInfo,
        call_type: config.callType,
        lang: config.language
          ? config.language
          : navigator.appName === 'Netscape'
          ? navigator.language.toLowerCase()
          : navigator.userLanguage.toLowerCase(),
      },
      function (err) {
        // 网络问题接口没有返回，直接使用本地验证码，走宕机模式
        // 这里可以添加用户的逻辑
        if (err && typeof config.offlineCb === 'function') {
          // 执行自己的宕机
          config.offlineCb()
          return
        }

        if (err) {
          callback(config._get_fallback_config())
        }
      },
      handleCb
    )
  }

  let reportError = function (config, url) {
    load(
      config,
      config.protocol,
      ['monitor.geetest.com'],
      '/monitor/send',
      {
        time: Date.now().getTime(),
        captcha_id: config.gt,
        challenge: config.challenge,
        exception_url: url,
        error_code: config.error_code,
      },
      function (err) {}
    )
  }

  let throwError = function (errorType, config, errObj) {
    let errors = {
      networkError: '网络错误',
      gtTypeError: 'gt字段不是字符串类型',
    }
    if (typeof config.onError === 'function') {
      config.onError({
        desc: errObj.desc,
        msg: errObj.msg,
        code: errObj.code,
      })
    } else {
      throw new Error(errors[errorType])
    }
  }

  let detect = function () {
    return window.Geetest || document.getElementById('gt_lib')
  }

  if (detect()) {
    status.slide = 'loaded'
  }
  let GeetestIsLoad = function (fname) {
    let GeetestIsLoad = false
    let tags = { js: 'script', css: 'link' }
    let tagname = tags[fname.split('.').pop()]
    if (tagname !== undefined) {
      let elts = document.getElementsByTagName(tagname)
      for (let i in elts) {
        if (
          (elts[i].href && elts[i].href.toString().indexOf(fname) > 0) ||
          (elts[i].src && elts[i].src.toString().indexOf(fname) > 0)
        ) {
          GeetestIsLoad = true
        }
      }
    }
    return GeetestIsLoad
  }
  window.initGeetest4 = function (userConfig, callback) {
    let config = new Config(userConfig)
    if (userConfig.https) {
      config.protocol = 'https://'
    } else if (!userConfig.protocol) {
      config.protocol = window.location.protocol + '//'
    }

    if (isObject(userConfig.getType)) {
      config._extend(userConfig.getType)
    }

    jsonp(config.apiServers, config.typePath, config, function (newConfig) {
      //错误捕获，第一个load请求可能直接报错
      var newConfig = camelizeKeys(newConfig)

      if (newConfig.status === 'error') {
        return throwError('networkError', config, newConfig)
      }

      let type = newConfig.type

      if (config.debug) {
        new _Object(newConfig)._extend(config.debug)
      }
      let init = function () {
        config._extend(newConfig)
        callback(new window.Geetest4(config))
      }

      callbacks[type] = callbacks[type] || []

      let s = status[type] || 'init'
      if (s === 'init') {
        status[type] = 'loading'

        callbacks[type].push(init)

        if (newConfig.gctPath) {
          load(
            config,
            config.protocol,
            Object.hasOwnProperty.call(config, 'staticServers')
              ? config.staticServers
              : newConfig.staticServers || config.staticServers,
            newConfig.gctPath,
            null,
            function (err) {
              if (err) {
                throwError('networkError', config, {
                  code: '60205',
                  msg: 'Network failure',
                  desc: {
                    detail: 'gct resource load timeout',
                  },
                })
              }
            }
          )
        }

        load(
          config,
          config.protocol,
          Object.hasOwnProperty.call(config, 'staticServers')
            ? config.staticServers
            : newConfig.staticServers || config.staticServers,
          newConfig.bypass || newConfig.staticPath + newConfig.js,
          null,
          function (err) {
            if (err) {
              status[type] = 'fail'
              throwError('networkError', config, {
                code: '60204',
                msg: 'Network failure',
                desc: {
                  detail: 'js resource load timeout',
                },
              })
            } else {
              status[type] = 'loaded'
              let cbs = callbacks[type]
              for (let i = 0, len = cbs.length; i < len; i = i + 1) {
                let cb = cbs[i]
                if (isFunction(cb)) {
                  cb()
                }
              }
              callbacks[type] = []
            }
          }
        )
      } else if (s === 'loaded') {
        // 判断gct是否需要重新加载
        if (!GeetestIsLoad(newConfig.gctPath)) {
          load(
            config,
            config.protocol,
            Object.hasOwnProperty.call(config, 'staticServers')
              ? config.staticServers
              : newConfig.staticServers || config.staticServers,
            newConfig.gctPath,
            null,
            function (err) {
              if (err) {
                throwError('networkError', config, {
                  code: '60205',
                  msg: 'Network failure',
                  desc: {
                    detail: 'gct resource load timeout',
                  },
                })
              }
            }
          )
        }
        return init()
      } else if (s === 'fail') {
        throwError('networkError', config, {
          code: '60204',
          msg: 'Network failure',
          desc: {
            detail: 'js resource load timeout',
          },
        })
      } else if (s === 'loading') {
        callbacks[type].push(init)
      }
    })
  }
})(window)
