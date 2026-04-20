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
  (if (empty? arr)
    nil
    (let [sum-magnitudes (reduce + (map Math/abs arr))
          sign (fn [x] (cond (pos? x) 1 (neg? x) -1 :else 0))
          product-signs (reduce * (map sign arr))]
      (* sum-magnitudes product-signs))))