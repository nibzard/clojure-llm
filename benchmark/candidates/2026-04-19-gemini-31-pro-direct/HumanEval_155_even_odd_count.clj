(defn even_odd_count [num]
  (let [digits (->> (str num)
                    (filter #(Character/isDigit %))
                    (map #(Character/digit % 10)))
        evens (count (filter even? digits))
        odds (- (count digits) evens)]
    [evens odds]))