<template>
  <div>
    <comment-component 
      v-for="comment in comments" 
      :key="comment.id"
      :comment="comment"
    />
  </div>
</template>

<script>
import CommentComponent from './CommentComponent.vue'
import axios from "axios";

export default {
  name: 'post-comments-component',
  components: {
    CommentComponent
  },
  props: {
      postId: {
        type: String
      },
      isCommentAdded: {
        type:Boolean
      }
  },
  data(){
    return {
      comments: []
    }
  },
  watch: {
    'isCommentAdded': function() {
      this.getPostComments();
      this.$emit('comment:actualized', null)
    }
  },
  methods:{
    getPostComments(){
        axios.get('http://localhost:8090/comments/post/' + this.postId, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('token')
        }
      })
      .then(data => {this.comments = data.data}).catch(e => console.log(e))
    },

  },
  mounted(){
    this.getPostComments()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>