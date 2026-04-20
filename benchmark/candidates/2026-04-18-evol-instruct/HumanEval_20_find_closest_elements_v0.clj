(defn longest-palindromic-subvector
  "Given a vector of values, return the longest contiguous subvector that is a palindrome.
If there are multiple longest palindromic subvectors, return the first one.
Return [] for an empty vector.

Examples:
>>> (longest-palindromic-subvector [1 2 3 2 1 4])
[1 2 3 2 1]
>>> (longest-palindromic-subvector [:a :b :b :a :c])
[:a :b :b :a]
>>> (longest-palindromic-subvector [1 2 3])
[1]"
  [v]
  (let [n (count v)]
    (if (zero? n)
      []
      (letfn [(pal? [sub] (= sub (vec (reverse sub))))
              (best-from-len [len]
                (first
                  (filter pal?
                          (map #(subvec v % (+ % len))
                               (range 0 (inc (- n len)))))))]
        (or (some best-from-len (range n 0 -1))
            [])))))