(defn is_Sum_Of_Powers_Of_Two
  "	Write a cljthon function to check whether the given number can be represented as sum of non-zero powers of 2 or not."
  [n]
  (cond
    (nil? n) false
    (neg? n) false
    (zero? n) false
    :else (every? true? (map #(zero? (mod n %)) [2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768 65536 131072 262144 524288 1048576 2097152 4194304 8388608 16777216 33554432 6710886