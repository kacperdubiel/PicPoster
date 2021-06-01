<template>
    <div>
      <form @submit.prevent="handleSubmit">
          <!-- Email input -->
          <div class="form-outline mb-4">
            <input type="email" id="form1Example1" class="form-control"
              v-model="email"
              :class="{'has-error' : submitting && invalidEmail}" />
            <label class="form-label" for="form1Example1">Email address</label>
          </div>

          <!-- Username input -->
          <div class="form-outline mb-4">
            <input id="form1Example1" class="form-control"
              v-model="username"
              :class="{'has-error' : submitting && invalidUsername}" />
            <label class="form-label" for="form1Example1">Username</label>
          </div>

          <!-- Password input -->
          <div class="form-outline mb-4">
            <input type="password" id="form1Example2" class="form-control" 
              v-model="repPassword"
              :class="{'has-error' : submitting && (invalidPassword || unproperPassword)}" />
            <label class="form-label" for="form1Example2">Password</label>
          </div>
          <p v-if="submitting && unproperPassword" class="error-message"> Password must containt 8 signs, at least one lowercase letter, one uppercase letter and number</p>
          <!-- Password input -->
          <div class="form-outline mb-4">
            <input type="password" id="form1Example2" class="form-control"
              v-model="password"
              :class="{'has-error' : submitting && (invalidPassword || unproperPassword)}"/>
            <label class="form-label" for="form1Example2">Repeat password</label>
          </div>
          <p v-if="submitting && notSamePassword" class="error-message"> Repeated password is not the same</p>
          <!-- 2 column grid layout for inline styling -->
          <!--<div class="row mb-4">
            <div class="col d-flex justify-content-center">
               Checkbox
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  value=""
                  id="form1Example3"
                  checked
                />
                <label class="form-check-label" for="form1Example3"> Accept policy </label>
              </div>
            </div>
          </div>-->
          <!-- Submit button -->
          <p v-if="error && submitting" class="error-message"> Please fill form fields correctly </p>
          <p v-if="serverErr && submitting" class="error-message"> That user already exists</p>
          <p v-if="success" class="success-message"> Thanks for joining us! Try to sign in. </p>
          <button type="submit" class="btn btn-primary btn-block">Sign up</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'register-component',
  data(){
    return{
      submitting: false,
      error: false,
      serverErr: false,
      success: false,
      email: "",
      username: "",
      password: "",
      repPassword: ""
    }
  },
  methods:
  {
    handleSubmit(){
      this.submitting = true;
      this.success = false;
      this.error = false;
      this.serverErr = false;

      if(this.invalidUsername || this.invalidPassword || this.invalidEmail || this.notSamePassword || this.unproperPassword){
        this.error = true;
        return
      }

      axios.post("http://localhost:8090/register", {
        login: this.username,
        password : this.password,
        email : this.email
      })
      .catch(e => {console.log(e); this.serverErr = true})
      .then(() => {
        if(this.serverErr){
          return
        }
        this.username = '';
        this.password = '';
        this.repPassword = '';
        this.email = '';
        this.error = false;
        this.success = true;
        this.submitting = false;
        this.serverErr = false;
      })
    }
  },
  computed:{
    invalidUsername(){
      return this.username === '';
    },
    invalidPassword(){
      return this.password === '' ;
    },
    invalidEmail(){
      return this.email === '';
    },
    notSamePassword(){
      return this.password != this.repPassword;
    },
    unproperPassword(){
      return !this.password.match(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/) //one number, one upper, one lower, 8 or more char
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>