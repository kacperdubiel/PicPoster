<template>
    <div id="posts-grid-container">
        <div id="posts-grid">
            <profile-post-miniature-component v-for="post in postsSource" 
                :key="post.id"
                :post="post"
                :user="user"/>
        </div>
    </div>
</template>

<script>
import ProfilePostMiniatureComponent from './ProfilePostMiniatureComponent.vue'
export default {
  name: 'profile-component',
  props: {
      userSource: {
          type: Object
      },
      postsSource: Array
  },
  components:{
      ProfilePostMiniatureComponent
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #posts-grid {
        display: flex;
        width: 50%;
        flex-wrap: wrap;
        justify-content: center;
        justify-content: space-around, space-between, space-evenly;
    }
    #posts-grid-container {
        display: flex;

        justify-content: center;
        margin-top: 3%;
    }
</style>
