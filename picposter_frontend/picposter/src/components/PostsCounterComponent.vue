<template>
    <span :key="postsAmount">{{ postsAmount }}</span>
</template>

<script>
import axios from "axios";

export default {
  name: 'posts-counter-component',
  props: {
      userId: {
        type: String
      },
      isPostsAmountChanged: Boolean
      
  },
  data(){
    return {
      postsAmount: 0
    }
  },
  watch: {
    'ispostsAmountChanged': function() {
      this.getUserPostsAmount();
    }
  },
  methods:{
    getUserPostsAmount(){
      axios.get('http://localhost:8090/posts/user/' + this.userId)
        .then(data => {this.postsAmount = data.data.length}).catch(e => alert(e))
    },

  },
  mounted(){
    this.getUserPostsAmount()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>