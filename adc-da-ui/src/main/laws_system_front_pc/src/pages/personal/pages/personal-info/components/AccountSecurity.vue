<!-- 账号安全 -->
<template>
  <div class="account-security">
    <div class="container">
      <Form ref="formCustom" :model="formCustom" :rules="ruleCustom" :label-width="100">
        <FormItem label="旧密码" prop="passwdOld">
          <Input type="password" v-model="formCustom.passwdOld" :style="{width:6+'rem'}"></Input>
        </FormItem>
        <FormItem label="新密码" prop="passwd">
          <Input type="password" v-model="formCustom.passwd" :style="{width:6+'rem'}"></Input>
        </FormItem>
        <FormItem label="再次输入新密码" prop="passwdCheck">
          <Input type="password" v-model="formCustom.passwdCheck" :style="{width:6+'rem'}"></Input>
        </FormItem>
        <FormItem>
          <Button type="primary" @click="handleSubmit('formCustom')" style="margin-left: 1rem">保存</Button>
          <Button @click="handleReset('formCustom')" style="margin-left: 0.3rem">重置</Button>
        </FormItem>
      </Form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'account-security',
  data () {
    const validatePassOld = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入您的密码'))
      }callback()
    }
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入您的新密码'))
      } else {
        if (value === this.formCustom.passwdOld) {
          callback(new Error('新密码跟旧密码重复！'))
        }
      }callback()
    }
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码！'))
      } else if (value !== this.formCustom.passwd) {
        callback(new Error('“两次输入密码不匹配！'))
      }callback()
    }

    return {
      formCustom: {
        passwdOld: '',
        passwd: '',
        passwdCheck: ''
      },
      ruleCustom: {
        passwdOld: [
          { validator: validatePassOld, trigger: 'blur' }
        ],
        passwd: [
          { validator: validatePass, trigger: 'blur' }
        ],
        passwdCheck: [
          { validator: validatePassCheck, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$Message.success('Success!')
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    handleReset (name) {
      this.$refs[name].resetFields()
    }
  }
}
</script>

<style lang="less">
  .account-security{
    width: 100%;
    height: 100%;
    .container{
      margin: 2rem 7rem;
    }
  }
</style>
