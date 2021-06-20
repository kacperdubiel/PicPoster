<template>
  <div>
     <form @submit.prevent="handleSubmit">
       <div id="add-comment-container">
        <input type="text" v-model="comment" :class="{'has-error' : submitting && invalidComment}" class="form-control" id="new-comment" placeholder="Dodaj komentarz..." autocomplete="off" maxlength="250">
        <button type="submit" class="btn btn-primary" id="new-comment-submit">Dodaj</button>
       </div>
        <p v-if="error && submitting" class="error-message">Twórz komentarz jest pusty!</p>
    </form>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'add-comment-component',
  data(){
    return {
      comment: "",
      submitting: false,
      success: false,
      error: false,
      postError: false
    }
  },
  props: {
    post: Object
  },
  methods:{
    blockComments(){
      if(this.post.allowComments == false){
        document.getElementById("new-comment").disabled=true;
        document.getElementById("new-comment-submit").disabled=true;
      }
    },
    handleSubmit(){
      this.$emit('comment:added', null)
      this.submitting= true;
      this.success= false;
      this.error= false;
      if(this.invalidComment){
        this.error = true;
        return;
      }
      console.log(this.post.id + "tu!");
      axios({
        method: 'post',
        headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
        url: "http://localhost:8090/comments",
        data:{
          comment: this.comment,
          post:{
            id: this.post.id
          },
          commentator:{
            id: localStorage.getItem('userId')
          }
        }
      })
      .catch(e => {console.log(e);})
      .then((response) => {
          if(response.status == 401){
            localStorage.removeItem('token')
            localStorage.removeItem('userId')
            this.$router.push(this.$route.query.redirect || '/')
          }
      })
      this.comment = '';
      this.error = false;
      this.submitting = false;
      this.success = true;
    }
  },

  computed:{
    invalidComment(){
      return this.comment==='';
    }
  },

  mounted(){
    this.blockComments();  
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#add-comment-container {
    display: flex;
    justify-content: space-between;
    margin-top: 7px;
}

#add-comment-container  > form {
    flex-grow: 4;
    margin-right: 10px;
}

#add-comment-container  > #form-group {
    margin-bottom: 0px;
}

#add-comment-container  > button {
    flex-grow: 1;
    padding: 2px 10px;
    height: 38px;
}
</style>