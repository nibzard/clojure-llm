(defn get_Char
  [strr]
  (let [ascii-sum (reduce + (map int strr))]
    (char (+ (int \a) (mod ascii-sum 26)))))