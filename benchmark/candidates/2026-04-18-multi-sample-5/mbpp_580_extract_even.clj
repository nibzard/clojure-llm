(defn extract_even
  "	Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (letfn [(process [x]
            (cond
              (vector? x) (vec (keep process x))
              (and (number? x) (even? x)) x
              :else nil))]
    (process test_tuple)))