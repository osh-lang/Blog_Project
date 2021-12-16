<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ui inverted vertical footer segment">
  <div class="ui center aligned container">
    <div class="ui stackable inverted divided grid">
      <div class="three wide column">
        <h4 class="ui inverted header">Group 1</h4>
        <div class="ui inverted link list">
          <a href="#" class="item">Link One</a>
          <a href="#" class="item">Link Two</a>
          <a href="#" class="item">Link Three</a>
          <a href="#" class="item">Link Four</a>
        </div>
      </div>
      <div class="three wide column">
        <h4 class="ui inverted header">Group 2</h4>
        <div class="ui inverted link list">
          <a href="#" class="item">Link One</a>
          <a href="#" class="item">Link Two</a>
          <a href="#" class="item">Link Three</a>
          <a href="#" class="item">Link Four</a>
        </div>
      </div>
      <div class="three wide column">
        <h4 class="ui inverted header">Group 3</h4>
        <div class="ui inverted link list">
          <a href="#" class="item">Link One</a>
          <a href="#" class="item">Link Two</a>
          <a href="#" class="item">Link Three</a>
          <a href="#" class="item">Link Four</a>
        </div>
      </div>
      <div class="seven wide column">
        <h4 class="ui inverted header">Footer Header</h4>
        <p>Extra space for a call to action inside the footer that could help re-engage users.</p>
      </div>
    </div>
    <div class="ui inverted section divider"></div>
    <img src="${pageContext.request.contextPath}/assets/themes/default/images/logo.png" class="ui centered mini image">
    <div class="ui horizontal inverted small divided link list">
      <a class="item" href="#">Site Map</a>
      <a class="item" href="#">Contact Us</a>
      <a class="item" href="#">Terms and Conditions</a>
      <a class="item" href="#">Privacy Policy</a>
    </div>
  </div>
</div>
