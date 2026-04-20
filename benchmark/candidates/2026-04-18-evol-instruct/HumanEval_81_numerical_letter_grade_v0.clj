(defn numerical_letter_grade
  "Given a collection of numeric grades, return a vector of letter grades.

  The input may be any sequential collection, including lazy sequences.
  Treat nil grades as \"E\".
  Use this scale:

    4.0 => \"A+\"
    > 3.7 => \"A\"
    > 3.3 => \"A-\"
    > 3.0 => \"B+\"
    > 2.7 => \"B\"
    > 2.3 => \"B-\"
    > 2.0 => \"C+\"
    > 1.7 => \"C\"
    > 1.3 => \"C-\"
    > 1.0 => \"D+\"
    > 0.7 => \"D\"
    > 0.0 => \"D-\"
    otherwise => \"E\"

  Examples:
  (numerical_letter_grade [4.0 3 1.7 nil 3.5])
  ;; => [\"A+\" \"B\" \"C\" \"E\" \"A-\"]

  (numerical_letter_grade (take 3 (range 4.0 1.0 -1.0)))
  ;; => [\"A+\" \"A\" \"A-\"]"
  [grades]
  (mapv (fn [g]
          (cond
            (nil? g) "E"
            (= g 4.0) "A+"
            (> g 3.7) "A"
            (> g 3.3) "A-"
            (> g 3.0) "B+"
            (> g 2.7) "B"
            (> g 2.3) "B-"
            (> g 2.0) "C+"
            (> g 1.7) "C"
            (> g 1.3) "C-"
            (> g 1.0) "D+"
            (> g 0.7) "D"
            (> g 0.0) "D-"
            :else "E"))
        grades))