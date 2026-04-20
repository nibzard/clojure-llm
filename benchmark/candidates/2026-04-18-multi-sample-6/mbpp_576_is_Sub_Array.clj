(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (seq A)
        b (seq B)]
    (cond
      (nil? a) true
      (nil? b) false
      :else
      (let [va (vec a)
            vb (vec b)
            na (count va)
            nb (count vb)]
        (cond
          (> na nb) false
          (= na 0) true
          :else
          (boolean
           (some true?
                 (for [i (range (inc (- nb na)))]
                   (= va (subvec vb i (+ i na))))))))))