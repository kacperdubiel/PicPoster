<template>
    <span :key="followersAmount">{{ followersAmount }}</span>
</template>

<script>
import axios from "axios";

export default {
  name: 'followers-counter-component',
  props: {
      userId: {
        type: String
      },
      isFollowersAmountChanged: Boolean
      
  },
  data(){
    return {
      followersAmount: 0
    }
  },
  watch: {
    'isFollowersAmountChanged': function() {
      this.getUserFollowersAmount();
    }
  },
  methods:{
    getUserFollowersAmount(){
      axios.get('http://localhost:8090/follows/followed/' + this.userId)
        .then(data => {this.followersAmount = data.data.length}).catch(e => alert(e))
    },

  },
  mounted(){
    this.getUserFollowersAmount()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>