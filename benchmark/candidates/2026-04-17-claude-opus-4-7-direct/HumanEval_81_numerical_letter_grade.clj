(defn numerical_letter_grade
  "Converts a list of numerical GPAs to letter grades based on the specified scale."
  [grades]
  (map (fn [gpa]
         (cond
           (= gpa 4.0) "A+"
           (> gpa 3.7) "A"
           (> gpa 3.3) "A-"
           (> gpa 3.0) "B+"
           (> gpa 2.7) "B"
           (> gpa 2.3) "B-"
           (> gpa 2.0) "C+"
           (> gpa 1.7) "C"
           (> gpa 1.3) "C-"
           (> gpa 1.0) "D+"
           (> gpa 0.7) "D"
           (> gpa 0.0) "D-"
           (= gpa 0.0) "E"))
       grades))