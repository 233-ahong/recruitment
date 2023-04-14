<template>
  <div>
    <div class='sidebar-1'>
      <ul>
        <li v-for="(heading, index) in headings" :key="index" :class="{ active: isActive(heading) }">
          <a :href="'#' + heading.id">{{ heading.text }}</a>
        </li>
      </ul>
    </div>
    <div class="content-1">
      <h1>标题</h1>
      <h2 id="section-1">副标题 1</h2>
      <div class='div-1'>123</div>
      <h2 id="section-2">副标题 2</h2>
      <div class='div-1'>123</div>
      <h2 id="section-3">副标题 3</h2>
      <div class='div-1' style='height: 1000px'>123</div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      headings: [
        { id: 'section-1', text: '副标题 1' },
        { id: 'section-2', text: '副标题 2' },
        { id: 'section-3', text: '副标题 3' },
      ],
      activeHeadingIndex: 0,
    };
  },
  methods: {
    isActive(heading) {
      return heading === this.headings[this.activeHeadingIndex];
    },
    handleScroll() {
      const scrollHeight = window.scrollY;
      for (let i = this.headings.length - 1; i >= 0; i--) {
        const heading = this.headings[i];
        const headingEl = document.getElementById(heading.id);
        const distanceFromTop = headingEl.offsetTop - 100; // 100 为阈值
        if (scrollHeight > distanceFromTop) {
          this.activeHeadingIndex = i;
          break;
        }
      }
    },
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll);
  },
};
</script>

<style>
.sidebar-1 {
  position: fixed;
  top: 60px;
  bottom: 0;
  width: 200px;
  background-color: #f0f0f0;
}
.sidebar-1 ul {
  margin: 0;
  padding: 0;
  list-style: none;
}
.sidebar-1 li {
  padding: 10px;
}
.sidebar-1 li.active {
  background-color: #e6f7ff;
}
.content-1 {
  margin-left: 200px;
  height: auto;
}
.div-1 {
  height: 300px;
}
</style>
