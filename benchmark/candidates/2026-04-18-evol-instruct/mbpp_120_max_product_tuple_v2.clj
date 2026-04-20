(defn max_abs_product
  "Return the maximum absolute product of any two numbers in the collection.

  Works with finite or infinite lazy sequences, so it should only realize
  as much input as needed.

  Examples:
  (max_abs_product [1 -3 2 4]) ;=> 12
  (max_abs_product [-10 2 -5]) ;=> 50
  (max_abs_product nil) ;=> nil"
  [xs]
  (when-let [s (seq xs)]
    (let [[mx1 mx2 mn1 mn2]
          (reduce
            (fn [[mx1 mx2 mn1 mn2] x]
              (let [x (Math/abs (double x))]
                [(max mx1 x)
                 (max mx2 (if (= x mx1) Double/NEGATIVE_INFINITY x))
                 (min mn1 x)
                 (min mn2 (if (= x mn1) Double/POSITIVE_INFINITY x))]))
            [Double/NEGATIVE_INFINITY Double/NEGATIVE_INFINITY
             Double/POSITIVE_INFINITY Double/POSITIVE_INFINITY]
            s)]
      (apply max
             (remove #(or (Double/isInfinite %) (Double/isNaN %))
                     [(* mx1 mx2) (* mn1 mn2)])))))