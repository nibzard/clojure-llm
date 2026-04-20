(defn extract_even
  "	Write a function to remove uneven elements in the nested mixed vector."
  [test_tuple]
  (cond
    (vector? test_tuple)
    (vec
     (keep (fn [x]
             (cond
               (vector? x)
               (let [v (extract_even x)]
                 (when (seq v) v))

               (and (number? x) (even? x))
               x

               :else
               nil))
           test_tuple))

    :else
    []))