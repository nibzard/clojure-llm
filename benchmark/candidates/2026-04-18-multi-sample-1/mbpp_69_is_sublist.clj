(defn is_sublist
  "	Write a function to check whether a list contains the given sublist or not."
  [l s]
  (let [l (seq l)
        s (seq s)]
    (cond
      (nil? s) true
      (nil? l) false
      :else
      (let [sv (vec s)
            n  (count sv)]
        (boolean
         (some true?
               (map #(= sv (vec (take n %)))
                    (take-while seq (iterate next l)))))))))