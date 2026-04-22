(ns learn.brainfuck
  "A Brainfuck interpreter in Clojure.")

(defn- parse-loops
  "Build a map of matching bracket positions: [ -> ] and ] -> [."
  [program]
  (let [len (count program)]
    (loop [i 0, stack [], matches {}]
      (if (>= i len)
        (if (seq stack)
          (throw (ex-info "Unmatched '[' in Brainfuck program" {:pos (peek stack)}))
          matches)
        (let [ch (.charAt program i)]
          (cond
            (= ch \[)
            (recur (inc i) (conj stack i) matches)

            (= ch \])
            (if (seq stack)
              (let [open (peek stack)]
                (recur (inc i) (pop stack) (assoc matches open i, i open)))
              (throw (ex-info "Unmatched ']' in Brainfuck program" {:pos i})))

            :else
            (recur (inc i) stack matches)))))))

(defn- make-tape
  "Create a tape of n cells, all zero."
  [n]
  (long-array (repeat n 0)))

(defn brainfuck
  "Interpret a Brainfuck program string and return the output as a string.

   Supported commands:
     >  increment data pointer
     <  decrement data pointer
     +  increment cell at pointer
     -  decrement cell at pointer
     .  output cell value as ASCII character
     ,  read one byte from input into cell
     [  jump past matching ] if cell is zero
     ]  jump back to matching [ if cell is non-zero

   Parameters:
     program - Brainfuck source code (string)
     input   - optional input string for ',' commands (defaults to \"\")

   Returns the output as a string."
  [program & [input]]
  (let [prog-len (count program)
        tape     (make-tape 30000)
        matches  (parse-loops program)
        in-bytes (if (string? input) (.getBytes input "UTF-8") (byte-array 0))]
    (loop [ip 0, dp 0, inp 0, out (StringBuilder.)]
      (if (>= ip prog-len)
        (.toString out)
        (let [cmd (.charAt program ip)]
          (case cmd
            \> (recur (inc ip) (inc dp) inp out)
            \< (recur (inc ip) (dec dp) inp out)
            \+ (do (aset tape dp (inc (aget tape dp)))
                   (recur (inc ip) dp inp out))
            \- (do (aset tape dp (dec (aget tape dp)))
                   (recur (inc ip) dp inp out))
            \. (do (.append out (char (aget tape dp)))
                   (recur (inc ip) dp inp out))
            \, (do (aset tape dp (if (< inp (alength in-bytes))
                                   (aget in-bytes inp)
                                   0))
                   (recur (inc ip) dp (inc inp) out))
            \[ (recur (if (zero? (aget tape dp))
                        (inc (get matches ip))
                        (inc ip))
                      dp inp out)
            \] (recur (if (zero? (aget tape dp))
                        (inc ip)
                        (get matches ip))
                      dp inp out)
            ;; skip non-command characters (comments)
            (recur (inc ip) dp inp out)))))))

(defn -main
  "Run a Brainfuck program from a file, or run a demo."
  [& args]
  (if (seq args)
    (let [program (slurp (first args))
          input   (second args)]
      (println (brainfuck program input)))
    (let [;; Hello World in Brainfuck
          hello-world "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."]
      (println "Hello World demo:")
      (println (brainfuck hello-world)))))
