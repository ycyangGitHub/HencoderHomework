package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class LessonPresenter(private var activity: LessonActivity) {
    companion object {
        private const val LESSON_PATH = "lessons"

    }

    private var lessons: List<Lesson> = ArrayList()

    private val type: Type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(lessons: List<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread { Utils.toast(message!!) }
            }
        })
    }

    fun showPlayback() {
        var playbackLessons: MutableList<Lesson> = ArrayList()
        activity.showResult(playbackLessons.filter { it.getState() === Lesson.State.PLAYBACK })
    }

}