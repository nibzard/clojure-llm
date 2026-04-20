(defn count-prefix-rotations
  "Return the smallest positive rotation count that turns a vector back into itself.
  If no positive rotation yields the original vector, return nil.

  Examples:
  (count-prefix-rotations [1 2 1 2]) => 2
  (count-prefix-rotations [:a :b :c]) => nil
  (count-prefix-rotations []) => nil
  (count-prefix-rotations nil) => nil"
  [v]
  (when (seq v)
    (let [n (count v)]
      (first
       (for [k (range 1 n)
             :let [rot (vec (concat (subvec (vec v) k) (subvec (vec v) 0 k)))] 
             :when (= (vec v) rot)]
         k)))))