<template>
    <div class="post-product-wrapper">
        <h2 class="title">发布新商品</h2>

        <div class="form-section">
            <el-card class="card">
                <p class="label">商品名</p>
                <el-input v-model="product.name" placeholder="请输入商品名" clearable />

                <p class="label">新旧程度</p>
                <el-input-number v-model="oldLevel" @change="handleChange" :min="1" :max="10" />

                <p class="label">价格</p>
                <el-input v-model="product.price" placeholder="请输入价格" prefix-icon="el-icon-money" clearable />
            </el-card>

            <el-card class="card">
                <p class="label">产品图</p>
                <el-upload action="http://localhost:31451/api/campus-product-sys/v1.0/file/upload"
                    list-type="picture-card" :on-success="handlePictureCardSuccess"
                    :on-preview="handlePictureCardPreview" :on-remove="handleRemove">
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="" />
                </el-dialog>
            </el-card>

            <el-card class="card">
                <p class="label">商品类别</p>
                <div class="category-list">
                    <span class="category-item" :class="{ selected: categorySelected.id === category.id }"
                        @click="categoryClick(category)" v-for="(category, index) in categoryList" :key="index">
                        {{ category.name }}
                    </span>
                </div>

                <p class="label">库存</p>
                <el-input-number v-model="inventory" @change="handleInventoryChange" :min="1" :max="10000" />
            </el-card>

            <el-card class="card">
                <p class="label">商品详情</p>
                <Editor height="300px" :receiveContent="product.detail" @on-receive="onReceive" />
            </el-card>
        </div>

        <div class="action-bar">
            <el-button type="primary" size="large" @click="postProduct">发布商品</el-button>
        </div>
    </div>
</template>
<script>
import Editor from "@/components/Editor"
export default {
    components: { Editor },
    name: 'PostProduct',
    data() {
        return {
            oldLevel: 9, // 默认九成新
            inventory: 1, // 默认库存
            product: {},
            categorySelected: {}, // 当前选中的商品类别
            dialogImageUrl: '',
            dialogVisible: false,
            coverList: [],
            categoryList: []
        };
    },
    created() {
        this.fetchCategoryList();
    },
    methods: {
        /**
         * 发布商品
         */
        postProduct() {
            if (this.product.name === undefined) {
                this.$notify.info({
                    duration: 1000,
                    title: '填写提醒',
                    message: '商品标题不能为空'
                });
                return;
            }
            if (this.product.price === undefined) {
                this.$notify.info({
                    duration: 1000,
                    title: '填写提醒',
                    message: '商品价格不能为空'
                });
                return;
            }
            if (this.coverList.length === 0) {
                this.$notify.info({
                    duration: 1000,
                    title: '上传提醒',
                    message: '请上传封面'
                });
                return;
            }
            if (this.product.inventory === undefined)
                this.product.inventory = 1;
            if (this.product.oldLevel === undefined)
                this.product.oldLevel = 9;
            this.product.coverList = this.coverList.join(',');
            // 商品封面需要处理
            this.$axios.post('/product/save', this.product).then(res => {
                const { data } = res;
                if (data.code === 200) {
                    this.$notify.success({
                        title: '发布操作',
                        message: data.msg
                    });
                    // 后续清除之后，跳转我的商品页面
                    // this.product = {};
                }
            }).catch(error => {
                this.$notify.error({
                    title: '发布操作',
                    message: error
                });
                console.log("新增商品异常:", error);
            });
        },
        /**
         * 富文本编辑器回调内容
         * @param {*} detail 
         */
        onReceive(detail) {
            this.product.detail = detail;
        },
        /**
         * 商品分类选中事件
         * @param {*} category 商品分类
         */
        categoryClick(category) {
            this.categorySelected = category;
            this.product.categoryId = category.id;
        },
        /**
         * 查询商品类别
         */
        fetchCategoryList() {
            this.$axios.post('/category/query', {}).then(res => {
                const { data } = res;
                if (data.code === 200) {
                    this.categoryList = data.data;
                    // 默认选中第一项
                    this.categoryClick(this.categoryList[0]);
                }
            }).catch(error => {
                console.log("查询商品类别信息异常:", error);
            });
        },
        /**
         * 新旧程度选择事件
         */
        handleChange() {
            console.log("新旧程度：", this.oldLevel);
            this.product.oldLevel = this.oldLevel;
        },
        /**
         * 库存设置事件
         */
        handleInventoryChange() {
            console.log("库存：", this.inventory);
            this.product.inventory = this.inventory;
        },
        /**
         * 封面上传成功响应事件
         * @param {*} file 
         * @param {*} fileList 
         */
        handlePictureCardSuccess(file, fileList) {
            this.coverList.push(file.data);
        },
        handleRemove(file, fileList) {
            this.coverList = fileList
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        }
    }
};
</script>
<style scoped lang="scss">
.post-product-wrapper {
    padding: 30px;
    max-width: 900px;
    margin: auto;
    background: #f9fcff;
    font-family: 'Helvetica Neue', sans-serif;

    .title {
        text-align: center;
        font-size: 26px;
        color: #3178c6;
        margin-bottom: 30px;
    }

    .form-section {
        display: flex;
        flex-direction: column;
        gap: 20px;
    }

    .card {
        padding: 20px;
        background: white;
        box-shadow: 0 4px 8px rgba(0, 123, 255, 0.1);
        border-radius: 12px;
    }

    .label {
        font-weight: 600;
        color: #2c3e50;
        margin-top: 10px;
        margin-bottom: 6px;
    }

    .category-list {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
    }

    .category-item {
        padding: 6px 16px;
        background-color: #e4f1ff;
        border-radius: 20px;
        font-size: 14px;
        cursor: pointer;
        transition: 0.3s all;
        color: #3178c6;
        border: 1px solid transparent;
    }

    .category-item.selected {
        background-color: #3178c6;
        color: white;
        border: 1px solid #1f5fa8;
    }

    .category-item:hover {
        background-color: #d0eaff;
    }

    .action-bar {
        text-align: center;
        margin-top: 30px;
    }

    .el-button {
        padding: 14px 40px;
        font-size: 16px;
        background-color: #3178c6;
        border-color: #3178c6;

        &:hover {
            background-color: #2563b9;
            border-color: #2563b9;
        }
    }
}
</style>