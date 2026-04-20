(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (seq A)
        b (seq B)]
    (cond
      (nil? b) true
      (nil? a) false
      :else
      (let [vb (vec b)
            lb (count vb)]
        (boolean
         (some true?
               (for [i (range 0 (inc (- (count a) lb)))]
                 (= vb (vec (take lb (drop i a))))))))))