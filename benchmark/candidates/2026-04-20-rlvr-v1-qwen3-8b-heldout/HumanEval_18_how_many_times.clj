(defn how_many_times
  " Find how many times a given substring can be found in the original string. Count overlaping cases.
  >>> (how_many_times \"\" \"a\")
  0
  >>> (how_many_times \"aaa\" \"a\")
  3
  >>> (how_many_times \"aaaa\" \"aa\")
  3"
  [string substring]
  (let [s (or string "")
        ss (or substring "")]
    (if (or (empty? s) (empty? ss))
      0
      (reduce
       +
       (map #(let [pos (count s)]
               (if (<= (- pos (count ss)) 0)
                 0
                 (count (re-seq (re-pattern (apply str (concat (repeat %1 (str %2)) "."))) s))))
            (range 1 (inc (count ss))))))))