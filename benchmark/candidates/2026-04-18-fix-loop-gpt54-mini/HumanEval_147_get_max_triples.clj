(defn get_max_triples
  "    You are given a positive integer n. You have to create an integer vector a of length n.
  For each i (1 ≤ i ≤ n), the value of a[i] = i * i - i + 1.
  Return the number of triples (a[i], a[j], a[k]) of a where i < j < k, 
      and a[i] + a[j] + a[k] is a multiple of 3.
      Example :
      >>> (get_max_triples 5)
      1
  Explanation: 
  a = [1, 3, 7, 13, 21]
  The only valid triple is (1, 7, 13)."
  [n]
  (let [q (quot n 3)
        r (mod n 3)
        c0 q
        c1 (+ q (if (>= r 1) 1 0))
        c2 (+ q (if (= r 2) 1 0))
        choose3 (fn [x]
                  (if (< x 3)
                    0
                    (/ (* x (dec x) (- x 2)) 6)))]
    (+ (choose3 c0)
       (choose3 c1)
       (choose3 c2)
       (* c0 c1 c2))))