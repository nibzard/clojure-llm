(defn prime-index-filter
  "Write a function that takes a collection and returns a lazy sequence of the elements
  whose 1-based positions are prime numbers.

  The function must work with any sequential collection and should preserve the order
  of the selected elements.

  Examples:
  >>> (prime-index-filter [\"a\" \"b\" \"c\" \"d\" \"e\" \"f\"])
  (\"b\" \"c\" \"e\")
  >>> (prime-index-filter '(10 20 30 40 50 60 70))
  (20 30 50 70)
  >>> (prime-index-filter [])
  ()

  Hint: positions are counted starting at 1, so the 2nd, 3rd, 5th, 7th, ... items are kept."
  [coll]
  (letfn [(prime? [n]
            (and (> n 1)
                 (not-any? #(zero? (mod n %))
                           (range 2 (inc (Math/sqrt n))))))]
    (keep-indexed (fn [idx x]
                    (when (prime? (inc idx))
                      x))
                  coll)))