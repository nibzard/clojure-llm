(defn numerical_letter_grade
  [grades]
  (let [grade-table [[4.0 "A+"]
                     [3.7 "A"]
                     [3.3 "A-"]
                     [3.0 "B+"]
                     [2.7 "B"]
                     [2.3 "B-"]
                     [2.0 "C+"]
                     [1.7 "C"]
                     [1.3 "C-"]
                     [1.0 "D+"]
                     [0.7 "D"]
                     [0.0 "D-"]]]
    (mapv (fn [g]
            (or (some (fn [[threshold letter]]
                        (when (>= g threshold)
                          letter))
                      grade-table)
                "E"))
          grades)))