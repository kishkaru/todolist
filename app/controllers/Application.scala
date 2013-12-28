package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._
import models.Task

import play.api.libs.json._

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.tasks)
  }

  val taskForm = Form(
    "label" -> nonEmptyText
  )

  def tasks = Action {
    Ok(views.html.index(taskForm))
  }

  def getJsonTasks = Action {
    val tasks = Task.all();
    Ok(Json.toJson(tasks))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Ok("deleted")
  }

}