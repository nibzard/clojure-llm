(defn encode
  [message]
  (let [vowel->shifted {\a \c, \e \g, \i \k, \o \q, \u \w
                        \A \C, \E \G, \I \K, \O \Q, \U \W}]
    (apply str
           (map (fn [ch]
                  (let [ch (get vowel->shifted ch ch)]
                    (if (Character/isUpperCase ch)
                      (Character/toLowerCase ch)
                      (Character/toUpperCase ch))))
                message))))