(defn problem-sequence-reductions
  ([f coll]
   (lazy-seq
     (when-let [s (seq coll)]
       (problem-sequence-reductions f (first s) (rest s)))))
  ([f init coll]
   (lazy-seq
     (cons init
           (when-let [s (seq coll)]
             (problem-sequence-reductions f (f init (first s)) (rest s)))))))