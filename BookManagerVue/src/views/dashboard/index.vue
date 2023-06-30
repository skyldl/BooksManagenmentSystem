<!--
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-02-21 18:13:21
-->
<template>
  <div class="dashboard-container">
    <div
      title="欢迎页面"
      style="
        padding: 50px;
        overflow: hidden;
        color: #409eff;
        font: lighter 16px 'Lucida Sans Unicode';
      "
      data-options="iconCls:'icon-heart',plain:true"
    >
      <b style="font-size: 36px; line-height: 30px; height: 30px">
        <i class="el-icon-reading" style="margin-right: 20px;"></i>欢迎使用图书管理系统</b
      >
      <hr />

      <img :src="require('@/assets/index.jpg')" style="width: 100%" />
      <!-- <div style="width: 100%; height: 800px;"  :style="{backgroundImage: 'url('+ require('@/assets/index.jpg') +')'}"></div> -->
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "Dashboard",
  computed: {
    ...mapGetters(["id", "name", "roles"]),
  },
};
</script>
