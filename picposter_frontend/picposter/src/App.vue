<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.shadow-box-sm {
  -webkit-box-shadow: 1px 1px 4px #888888;  /* Safari 3-4, iOS 4.0.2 - 4.2, Android 2.3+ */
  -moz-box-shadow:    1px 1px 4px #888888;  /* Firefox 3.5 - 3.6 */
  box-shadow:         1px 1px 4px #888888;  /* Opera 10.5, IE 9, Firefox 4+, Chrome 6+, iOS 5 */
}

.shadow-box {
  -webkit-box-shadow: 2px 2px 6px #888888;  /* Safari 3-4, iOS 4.0.2 - 4.2, Android 2.3+ */
  -moz-box-shadow:    2px 2px 6px #888888;  /* Firefox 3.5 - 3.6 */
  box-shadow:         2px 2px 6px #888888;  /* Opera 10.5, IE 9, Firefox 4+, Chrome 6+, iOS 5 */
}

</style>
