(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (seq A)
        b (seq B)]
    (cond
      (nil? a) true
      (nil? b) false
      :else
      (let [n (count a)]
        (boolean
         (some #(= a %)
               (map #(subvec b % (+ % n))
                    (range 1 (inc (- (count b) n))))))))))