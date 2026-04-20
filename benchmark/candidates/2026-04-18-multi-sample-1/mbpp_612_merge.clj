(defn merge
  "	Write a cljthon function which takes a list of lists, where each sublist has two elements, and returns a list of two lists where the first list has the first element of each sublist and the second one has the second."
  [lst]
  (let [[xs ys] (reduce (fn [[acc1 acc2] [a b]]
                          [(conj acc1 a) (conj acc2 b)])
                        [[] []]
                        lst)]
    [(seq xs) (seq ys)]))