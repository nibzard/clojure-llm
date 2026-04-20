(defn count_up_to
  "Return a lazy sequence of all prime numbers less than n.

  The function must be lazy enough to work with very large n without realizing
  the entire result immediately.

  Examples:
  >>> (take 2 (count_up_to 5))
  (2 3)
  >>> (take 4 (count_up_to 11))
  (2 3 5 7)
  >>> (count_up_to 0)
  ()
  >>> (take 8 (count_up_to 20))
  (2 3 5 7 11 13 17 19)
  >>> (take 0 (count_up_to 1000000))
  ()"
  [n]
  (letfn [(prime? [x]
            (and (> x 1)
                 (not-any? #(zero? (mod x %))
                           (take-while #(<= (* % %) x) (count-up-to-seq 2)))))]
    (letfn [(count-up-to-seq [start]
              (lazy-seq
                (when (< start n)
                  (if (prime? start)
                    (cons start (count-up-to-seq (inc start)))
                    (count-up-to-seq (inc start))))))]
      (count-up-to-seq 2))))