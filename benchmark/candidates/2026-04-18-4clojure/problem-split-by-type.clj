(defn problem-split-by-type [a]
  (vals
   (reduce (fn [m x]
             (update m (type x) (fnil conj []) x))
           {}
           a)))