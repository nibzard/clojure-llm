(defn reverse_vowels [str1]
  (if (nil? str1)
    nil
    (let [vowels #{\a \e \i \o \u \A \E \I \O \U}
          rev-vowels (reverse (filter vowels str1))]
      (apply str
             (first
              (reduce (fn [[acc rvs] c]
                        (if (vowels c)
                          [(conj acc (first rvs)) (rest rvs)]
                          [(conj acc c) rvs]))
                      [[] rev-vowels]
                      str1))))))