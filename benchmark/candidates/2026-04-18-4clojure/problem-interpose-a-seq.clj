(defn problem-interpose-a-seq [a b c]
  (if (empty? b)
    []
    (reduce (fn [acc x] (conj acc a x)) [(first b)] (rest b))))