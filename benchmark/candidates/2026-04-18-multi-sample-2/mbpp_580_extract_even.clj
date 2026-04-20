(defn extract_even
  "	Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (letfn [(even-only [x]
            (cond
              (vector? x)
              (->> x
                   (map even-only)
                   (remove nil?)
                   vec)

              (sequential? x)
              (->> x
                   (map even-only)
                   (remove nil?))

              (number? x)
              (when (even? x) x)

              :else nil))]
    (even-only test_tuple)))