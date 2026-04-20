(defn even_odd_palindrome
  "Given a positive integer n, return a vector that has the number of even and odd
  integer palindromes that fall within the range(1, n), inclusive."
  [n]
  (let [palindromes (filter (fn [x]
                              (let [s (str x)]
                                (= s (apply str (reverse s)))))
                            (range 1 (inc n)))
        evens (filter even? palindromes)
        odds (filter odd? palindromes)]
    [(count evens) (count odds)]))