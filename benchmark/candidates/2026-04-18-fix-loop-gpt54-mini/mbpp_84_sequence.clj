(defn sequence
  "	Write a function to find the nth number in the newman conway sequence."
  [n]
  (let [memo-q (atom {1 1, 2 1})
        q (fn q [k]
            (if-let [v (@memo-q k)]
              v
              (let [v (q (- k (q (dec k))))]
                (swap! memo-q assoc k v)
                v)))]
    (q n)))