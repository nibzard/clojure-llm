(defn get_closest_vowel [word]
  (let [vowel? #{\a \e \i \o \u \A \E \I \O \U}
        cons? #(not (vowel? %))]
    (or (first (for [i (range (- (count word) 3) -1 -1)
                     :let [c1 (nth word i)
                           v  (nth word (inc i))
                           c2 (nth word (+ i 2))]
                     :when (and (cons? c1) (vowel? v) (cons? c2))]
                 (str v)))
        "")))