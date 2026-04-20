(defn prod_signs
  "You are given a vector arr of integers and you need to return
  sum of magnitudes of integers multiplied by product of all signs
  of each number in the vector, represented by 1, -1 or 0.
  Note: return nil for empty arr.
  Example:
  >>> (prod_signs [1 2 2 -4])
  9
  >>> (prod_signs [0 1])
  0
  >>> (prod_signs [])
  nil"
  [arr]
  (when (seq arr)
    (let [sum-magnitudes (reduce #(+ %1 (Math/abs %2)) 0 arr)
          sign-product (reduce #(* %1 (Long/signum %2)) 1 arr)]
      (* sum-magnitudes sign-product))))