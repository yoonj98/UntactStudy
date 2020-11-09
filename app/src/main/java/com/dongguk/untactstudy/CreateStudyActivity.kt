package com.dongguk.untactstudy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_study.*


class CreateStudyActivity : AppCompatActivity() {

    var spinneryear: Spinner? = null
    var spinnermonth: Spinner? = null
    var spinnerday: Spinner? = null


    var spinner1: Spinner? = null
    var spinner2: Spinner? = null


    var dataAdapter1: ArrayAdapter<CharSequence>? = null
    var dataAdapter2: ArrayAdapter<CharSequence?>? = null
    var dataAdapter3: ArrayAdapter<CharSequence>? = null
    var dataAdapter4: ArrayAdapter<CharSequence>? = null
    var dataAdapter5: ArrayAdapter<CharSequence>? = null

    var first = ""
    var second = ""

    var year = "2020"
    var month = "1"
    var day = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_study)

        spinner1 = findViewById(R.id.createStudySpinner1stSort) as Spinner
        spinner2 = findViewById(R.id.createStudySpinner2ndSort) as Spinner
        spinneryear = findViewById(R.id.createStudySpinnerYear) as Spinner
        spinnermonth = findViewById(R.id.createStudySpinnerMonth) as Spinner
        spinnerday = findViewById(R.id.createStudySpinnerDay) as Spinner


        var btn = findViewById(R.id.createStudyButton) as Button
        var cancelbtn = findViewById(R.id.createStudyCancel) as Button
        var plusbtn = findViewById(R.id.createStudyCreateIndex) as Button

        dataAdapter1 = ArrayAdapter.createFromResource(
            this,
            R.array.study_array,
            android.R.layout.simple_spinner_item
        )
        dataAdapter1!!.setDropDownViewResource(android.R.layout.simple_spinner_item)

        spinner1?.setAdapter(dataAdapter1)
        spinner1?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (dataAdapter1!!.getItem(position) == "어학") {
                    first = "어학"
                    dataAdapter2 = ArrayAdapter.createFromResource(
                        this@CreateStudyActivity,
                        R.array.language_array,
                        android.R.layout.simple_spinner_item
                    )
                    dataAdapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_item)
                    spinner2?.setAdapter(dataAdapter2)
                    spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            second = dataAdapter2!!.getItem(position).toString()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                } else if (dataAdapter1!!.getItem(position) == "자격증") {
                    first = "자격증"
                    dataAdapter2 = ArrayAdapter.createFromResource(
                        this@CreateStudyActivity,
                        R.array.certification_array,
                        android.R.layout.simple_spinner_item
                    )
                    dataAdapter2!!.setDropDownViewResource(android.R.layout.simple_spinner_item)
                    spinner2?.setAdapter(dataAdapter2)
                    spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            second = dataAdapter2!!.getItem(position).toString()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        dataAdapter3 = ArrayAdapter.createFromResource(
            this@CreateStudyActivity,
            R.array.studyFinishYear,
            android.R.layout.simple_spinner_item
        )

        dataAdapter4 = ArrayAdapter.createFromResource(
            this@CreateStudyActivity,
            R.array.studyFinishMonth,
            android.R.layout.simple_spinner_item
        )

        dataAdapter5 = ArrayAdapter.createFromResource(
            this@CreateStudyActivity,
            R.array.studyFinishDay,
            android.R.layout.simple_spinner_item
        )

        spinneryear?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                year = dataAdapter3!!.getItem(position).toString()
            }
        }

        spinnermonth?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                month = dataAdapter4!!.getItem(position).toString()
            }
        }

        spinnerday?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                day = dataAdapter5!!.getItem(position).toString()
            }
        }

        //목차 추가 버튼

        var relativeLayout: RelativeLayout
        relativeLayout = findViewById(R.id.relativeLayout);
        var indexNum: Int = 1

        plusbtn.setOnClickListener(View.OnClickListener {

            indexNum += 1
            for (i in 0 until indexNum) {
                val et = EditText(applicationContext)
                val p = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                et.layoutParams = p
                et.setText("스터디 목차 " + i)
                et.id = indexNum
                relativeLayout.addView(et)
            }
        })

        //취소 버튼

        cancelbtn.setOnClickListener(object : View.OnClickListener {

            override fun onClick(view: View?) {
                startActivity(Intent(this@CreateStudyActivity, MainActivity::class.java)) //메인 액티비티로 이동
            }
        })

        //확인 버튼

        btn.setOnClickListener(object : View.OnClickListener {

           override fun onClick(view: View?) {

               var fbAuth: FirebaseAuth? = null
               fbAuth = FirebaseAuth.getInstance()

               val uid = fbAuth.uid

                val createStudyName = createStudyName.text.toString()
                val createStudyInfo = createStudyInfo.text.toString()
                val createStudyMemberAmount = createStudyMemberAmount.text.toString()
                val createStudyIndex = createStudyIndex.text.toString()

                if (second != "2차 분류를 선택하세요.") {

                    val study = StudyModel(createStudyName, createStudyInfo, createStudyMemberAmount, year, month, day, first,  second, createStudyIndex, uid.toString())

                    val firestore = FirebaseFirestore.getInstance()
                    firestore.collection("studyInfo")
                        .document(uid.toString())
                        .set(study)
                        .addOnCompleteListener{
                            println("DB 저장 완료")
                         startActivity(Intent(this@CreateStudyActivity, MainActivity::class.java)) //메인 액티비티로 이동
                        }
                }
            }
        })
    }
}