/*
 * Copyright (c) 2008-2014 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.operation;

import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ReadBinding;
import org.bson.BsonDocument;
import org.mongodb.CommandResult;
import org.mongodb.MongoFuture;

import static org.mongodb.operation.CommandOperationHelper.executeWrappedCommandProtocol;
import static org.mongodb.operation.CommandOperationHelper.executeWrappedCommandProtocolAsync;

/**
 * An operation that executes an arbitrary command that reads from the server.
 *
 * @since 3.0
 */
public class CommandReadOperation implements AsyncReadOperation<CommandResult>, ReadOperation<CommandResult> {
    private final String database;
    private final BsonDocument commandDocument;

    public CommandReadOperation(final String database, final BsonDocument command) {
        this.database = database;
        this.commandDocument = command;
    }

    @Override
    public CommandResult execute(final ReadBinding binding) {
        return executeWrappedCommandProtocol(database, commandDocument, binding);
    }

    @Override
    public MongoFuture<CommandResult> executeAsync(final AsyncReadBinding binding) {
        return executeWrappedCommandProtocolAsync(database, commandDocument, binding);
    }
}