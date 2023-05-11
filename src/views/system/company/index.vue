<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司类型" prop="companyType">
        <el-select v-model="queryParams.companyType" placeholder="请选择公司类型" clearable>
          <el-option
            v-for="dict in sys_company_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="公司状态" prop="companyStatus">
        <el-select v-model="queryParams.companyStatus" placeholder="请选择公司状态" clearable>
          <el-option
            v-for="dict in sys_company_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公司ID" align="center" prop="id" />
      <el-table-column label="公司名称" align="center" prop="name" />
      <el-table-column label="公司描述" align="center" prop="description" />
      <el-table-column label="公司地址" align="center" prop="address" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="联系邮箱" align="center" prop="email" />
      <el-table-column label="公司LOGO" align="center" prop="logoUrl" >
        <template #default='scope'>
          <el-image  :src='scope.row.logoUrl'/>
        </template>
      </el-table-column>
      <el-table-column label="公司营业执照" align="center" prop="licenseUrl" >
        <template #default='scope'>
          <el-image  :src='scope.row.licenseUrl'/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="公司类型" align="center" prop="companyType">
        <template #default="scope">
          <dict-tag :options="sys_company_type" :value="scope.row.companyType"/>
        </template>
      </el-table-column>
      <el-table-column label="公司状态" align="center" prop="companyStatus">
        <template #default="scope">
          <dict-tag :options="sys_company_status" :value="scope.row.companyStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            type="text"
            icon="Edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改公司对话框 -->
    <el-dialog :title="title" v-model="open" width="80%" append-to-body>
      <el-form ref="companyRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="name">
          <el-input :disabled='true' v-model="form.name" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司描述" prop="description">
          <el-input :disabled='true' v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="公司地址" prop="address">
          <el-input :disabled='true' v-model="form.address" placeholder="请输入公司地址" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input :disabled='true' v-model="form.phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="联系邮箱" prop="email">
          <el-input :disabled='true' v-model="form.email" placeholder="请输入联系邮箱" />
        </el-form-item>
        <el-form-item label="公司LOGO的URL" prop="logoUrl">
          <el-image :src="form.logoUrl" placeholder="请输入公司LOGO的URL" />
        </el-form-item>
        <el-form-item label="公司营业执照的URL" prop="licenseUrl">
          <el-image :src="form.licenseUrl"/>
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
                          v-model="form.createdTime"
                          type="date"
                          :disabled='true'
                          value-format="YYYY-MM-DD"
                          placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable
                          v-model="form.updatedTime"
                          type="date"
                          :disabled='true'
                          value-format="YYYY-MM-DD"
                          placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="公司类型" prop="companyType">
          <el-select v-model="form.companyType" placeholder="请选择公司类型">
            <el-option
              v-for="dict in sys_company_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公司状态" prop="companyStatus">
          <el-select v-model="form.companyStatus" placeholder="请选择公司状态">
            <el-option
              v-for="dict in sys_company_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { listCompany, getCompany, updateCompany } from "@/api/system/company";
import { ElMessage, ElMessageBox } from 'element-plus'
import { getCurrentInstance, reactive, ref, toRefs } from 'vue'
import { parseTime } from '@/utils/recruitment.js'

const { proxy } = getCurrentInstance();
const { sys_company_status, sys_company_type } = proxy.useDict('sys_company_status', 'sys_company_type');

const companyList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    description: null,
    address: null,
    phone: null,
    email: null,
    logoUrl: null,
    createdTime: null,
    updatedTime: null,
    companyType: null,
    companyStatus: null
  },
  rules: {
    name: [
      { required: true, message: "公司名称不能为空", trigger: "blur" }
    ],
    description: [
      { required: true, message: "公司描述不能为空", trigger: "blur" }
    ],
    address: [
      { required: true, message: "公司地址不能为空", trigger: "blur" }
    ],
    phone: [
      { required: true, message: "联系电话不能为空", trigger: "blur" }
    ],
    email: [
      { required: true, message: "联系邮箱不能为空", trigger: "blur" }
    ],
    logoUrl: [
      { required: true, message: "公司LOGO的URL不能为空", trigger: "blur" }
    ],
    companyType: [
      { required: true, message: "公司类型不能为空", trigger: "change" }
    ],
    companyStatus: [
      { required: true, message: "公司状态不能为空", trigger: "change" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询公司列表 */
function getList() {
  loading.value = true;
  listCompany(queryParams.value).then(response => {
    companyList.value = response.data.records;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    name: null,
    description: null,
    address: null,
    phone: null,
    email: null,
    logoUrl: null,
    createdTime: null,
    updatedTime: null,
    companyType: null,
    companyStatus: null
  };
  if (proxy.$refs["companyRef"]){
    proxy.$refs["companyRef"].resetFields();
  }
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  if (proxy.$refs["queryRef"]){
    proxy.$refs["queryRef"].resetFields();
  }
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const id = row.id || ids.value
  getCompany(id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改公司";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["companyRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCompany(form.value).then(response => {
          ElMessage.success("修改成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}
getList();
</script>
