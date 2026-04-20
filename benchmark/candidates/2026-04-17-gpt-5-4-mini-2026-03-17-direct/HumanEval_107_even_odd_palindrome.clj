(defn even_odd_palindrome
  [n]
  (let [palindrome? (fn [x]
                      (let [s (str x)]
                        (= s (apply str (reverse s)))))
        pals (filter palindrome? (range 1 (inc n)))]
    [(count (filter even? pals))
     (count (filter odd? pals))]))