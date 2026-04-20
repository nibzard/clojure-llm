(defn extract_even
  "Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (letfn [(even-item? [x]
            (cond
              (integer? x) (even? x)
              (vector? x) true
              :else false))
          (process [v]
            (vec
             (keep (fn [x]
                     (cond
                       (vector? x) (process x)
                       (even-item? x) x
                       :else nil))
                   v)))]
    (when (vector? test_tuple)
      (process test_tuple))))