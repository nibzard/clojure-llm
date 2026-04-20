(defn even_odd_palindrome [n]
  (let [palindromes (filter #(let [s (str %)] (= (seq s) (reverse s)))
                            (range 1 (inc n)))
        evens (count (filter even? palindromes))]
    [evens (- (count palindromes) evens)]))