(defn extract_even
  "Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (letfn [(process [x]
            (cond
              (number? x) (if (even? x) x nil)
              (vector? x) (->> x (map process) (remove nil?) vec)
              :else nil))]
    (process test_tuple)))