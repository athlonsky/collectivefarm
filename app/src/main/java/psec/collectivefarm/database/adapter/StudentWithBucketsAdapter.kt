package psec.collectivefarm.database.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import psec.collectivefarm.database.entity.Bucket
import psec.collectivefarm.database.entity.Student
import psec.collectivefarm.databinding.RecyclerViewStudentBinding

class StudentWithBucketsAdapter : RecyclerView.Adapter<StudentWithBucketsAdapter.ViewHolder>() {
    var student = mutableListOf<Student>()
    var bucket = mutableListOf<Bucket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerViewStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewName.text = student[position].name
        holder.binding.textViewValue.text = bucket[position].value.toString()
    }

    override fun getItemCount(): Int {
        return student.size
    }

    inner class ViewHolder(val binding: RecyclerViewStudentBinding) :
        RecyclerView.ViewHolder(binding.root)
}