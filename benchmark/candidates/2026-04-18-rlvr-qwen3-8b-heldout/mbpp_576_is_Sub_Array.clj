(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (or A [])
        b (or B [])]
    (cond
      (empty? a) true
      (empty? b) false
      :else
      (let [n (count a)
            m (count b)]
        (boolean
         (some #(= a %)
               (for [i (range (- m n 1) -1 -1)]
                 (subvec b i (+ i n)))))))))