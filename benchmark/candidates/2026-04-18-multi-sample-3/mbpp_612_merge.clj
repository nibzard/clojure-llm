(defn merge
  "	Write a cljthon function which takes a list of lists, where each sublist has two elements, and returns a list of two lists where the first list has the first element of each sublist and the second one has the second."
  [lst]
  (let [[xs ys] (reduce (fn [[acc1 acc2] pair]
                          [(conj acc1 (first pair))
                           (conj acc2 (second pair))])
                        [[] []]
                        lst)]
    (list xs ys)))